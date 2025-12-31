import { describe, it, expect, beforeEach, vi } from 'vitest';
import { useAuthStore } from '../authStore';
import apiClient from '@/services/api/client';

vi.mock('@/services/api/client', () => ({
  default: {
    get: vi.fn(),
    post: vi.fn(),
    put: vi.fn(),
    delete: vi.fn(),
  },
}));

describe('authStore', () => {
  beforeEach(() => {
    // Reset store state before each test
    useAuthStore.setState({
      username: null,
      password: null,
      isAuthenticated: false,
    });
    vi.clearAllMocks();
  });

  it('should have initial state', () => {
    const state = useAuthStore.getState();
    expect(state.username).toBeNull();
    expect(state.password).toBeNull();
    expect(state.isAuthenticated).toBe(false);
  });

  it('should login with valid credentials', async () => {
    vi.mocked(apiClient.get).mockResolvedValue({ data: {} });

    const { login } = useAuthStore.getState();
    await login('testuser', 'testpass');

    const state = useAuthStore.getState();
    expect(state.username).toBe('testuser');
    expect(state.password).toBe('testpass');
    expect(state.isAuthenticated).toBe(true);
    expect(apiClient.get).toHaveBeenCalledWith('/search/person/test');
  });

  it('should throw error on login with invalid credentials', async () => {
    vi.mocked(apiClient.get).mockRejectedValue(new Error('401 Unauthorized'));

    const { login } = useAuthStore.getState();

    await expect(login('invalid', 'wrong')).rejects.toThrow(
      'Invalid credentials'
    );

    const state = useAuthStore.getState();
    expect(state.isAuthenticated).toBe(false);
  });

  it('should logout and clear state', () => {
    // Set authenticated state
    useAuthStore.setState({
      username: 'testuser',
      password: 'testpass',
      isAuthenticated: true,
    });

    const { logout } = useAuthStore.getState();
    logout();

    const state = useAuthStore.getState();
    expect(state.username).toBeNull();
    expect(state.password).toBeNull();
    expect(state.isAuthenticated).toBe(false);
  });

  it('should persist state to localStorage on login', async () => {
    vi.mocked(apiClient.get).mockResolvedValue({ data: {} });

    const { login } = useAuthStore.getState();
    await login('admin', 'admin');

    // Check that persist middleware saved to localStorage
    const stored = localStorage.getItem('auth-storage');
    expect(stored).toBeTruthy();

    if (stored) {
      const parsed = JSON.parse(stored);
      expect(parsed.state.username).toBe('admin');
      expect(parsed.state.isAuthenticated).toBe(true);
    }
  });

  it('should clear localStorage on logout', async () => {
    // Setup authenticated state
    vi.mocked(apiClient.get).mockResolvedValue({ data: {} });

    const { login, logout } = useAuthStore.getState();
    await login('admin', 'admin');

    logout();

    const stored = localStorage.getItem('auth-storage');
    if (stored) {
      const parsed = JSON.parse(stored);
      expect(parsed.state.isAuthenticated).toBe(false);
    }
  });

  it('should not persist password to localStorage', async () => {
    vi.mocked(apiClient.get).mockResolvedValue({ data: {} });

    const { login } = useAuthStore.getState();
    await login('admin', 'secretpassword');

    const stored = localStorage.getItem('auth-storage');
    expect(stored).toBeTruthy();

    if (stored) {
      const parsed = JSON.parse(stored);
      expect(parsed.state.password).toBeUndefined();
      expect(parsed.state.username).toBe('admin');
    }
  });
});
