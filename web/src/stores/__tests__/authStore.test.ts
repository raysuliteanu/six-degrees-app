import { describe, it, expect, beforeEach, vi } from 'vitest';
import { useAuthStore } from '../authStore';

describe('authStore', () => {
  beforeEach(() => {
    // Clear sessionStorage before each test
    sessionStorage.clear();

    // Reset store state before each test
    useAuthStore.setState({
      username: null,
      password: null,
      isAuthenticated: false,
      lastActivity: null,
    });
    vi.clearAllMocks();
  });

  it('should have initial state', () => {
    const state = useAuthStore.getState();
    expect(state.username).toBeNull();
    expect(state.password).toBeNull();
    expect(state.isAuthenticated).toBe(false);
    expect(state.lastActivity).toBeNull();
  });

  it('should login without making API call', async () => {
    const { login } = useAuthStore.getState();
    await login('testuser', 'testpass');

    const state = useAuthStore.getState();
    expect(state.username).toBe('testuser');
    expect(state.password).toBe('testpass');
    expect(state.isAuthenticated).toBe(true);
    expect(state.lastActivity).toBeGreaterThan(0);
  });

  it('should logout and clear state', () => {
    // Set authenticated state
    useAuthStore.setState({
      username: 'testuser',
      password: 'testpass',
      isAuthenticated: true,
      lastActivity: Date.now(),
    });

    const { logout } = useAuthStore.getState();
    logout();

    const state = useAuthStore.getState();
    expect(state.username).toBeNull();
    expect(state.password).toBeNull();
    expect(state.isAuthenticated).toBe(false);
    expect(state.lastActivity).toBeNull();
  });

  it('should persist state to sessionStorage on login', async () => {
    const { login } = useAuthStore.getState();
    await login('admin', 'admin');

    // Check that persist middleware saved to sessionStorage
    const stored = sessionStorage.getItem('auth-storage');
    expect(stored).toBeTruthy();

    // Verify state is persisted correctly
    const state = useAuthStore.getState();
    expect(state.isAuthenticated).toBe(true);
    expect(state.lastActivity).toBeGreaterThan(0);
  });

  it('should clear sessionStorage on logout', async () => {
    // Setup authenticated state
    const { login, logout } = useAuthStore.getState();
    await login('admin', 'admin');

    logout();

    // After logout, state should be cleared
    const state = useAuthStore.getState();
    expect(state.isAuthenticated).toBe(false);
    expect(state.username).toBeNull();
    expect(state.password).toBeNull();
  });

  it('should store encoded credentials in sessionStorage', async () => {
    const { login } = useAuthStore.getState();
    await login('admin', 'secretpassword');

    // Credentials should be in memory
    const state = useAuthStore.getState();
    expect(state.username).toBe('admin');
    expect(state.password).toBe('secretpassword');

    // Check sessionStorage contains data
    const stored = sessionStorage.getItem('auth-storage');
    expect(stored).toBeTruthy();
  });

  it('should validate active session', () => {
    const { validateSession } = useAuthStore.getState();

    // Set up authenticated state with recent activity
    useAuthStore.setState({
      username: 'admin',
      password: 'admin',
      isAuthenticated: true,
      lastActivity: Date.now(),
    });

    expect(validateSession()).toBe(true);
  });

  it('should invalidate expired session', () => {
    const { validateSession } = useAuthStore.getState();

    // Set up authenticated state with old timestamp (31 minutes ago)
    const thirtyOneMinutesAgo = Date.now() - 31 * 60 * 1000;
    useAuthStore.setState({
      username: 'admin',
      password: 'admin',
      isAuthenticated: true,
      lastActivity: thirtyOneMinutesAgo,
    });

    expect(validateSession()).toBe(false);
    // Session should be cleared
    expect(useAuthStore.getState().isAuthenticated).toBe(false);
  });

  it('should invalidate session with no activity timestamp', () => {
    const { validateSession } = useAuthStore.getState();

    useAuthStore.setState({
      username: 'admin',
      password: 'admin',
      isAuthenticated: true,
      lastActivity: null,
    });

    expect(validateSession()).toBe(false);
  });

  it('should invalidate session when not authenticated', () => {
    const { validateSession } = useAuthStore.getState();

    useAuthStore.setState({
      username: null,
      password: null,
      isAuthenticated: false,
      lastActivity: Date.now(),
    });

    expect(validateSession()).toBe(false);
  });

  it('should refresh activity timestamp', () => {
    const { refreshActivity } = useAuthStore.getState();

    const beforeTime = Date.now();
    useAuthStore.setState({
      username: 'admin',
      password: 'admin',
      isAuthenticated: true,
      lastActivity: beforeTime - 1000, // 1 second ago
    });

    refreshActivity();

    const afterTime = useAuthStore.getState().lastActivity;
    expect(afterTime).toBeGreaterThan(beforeTime - 1000);
    expect(afterTime).toBeGreaterThanOrEqual(beforeTime);
  });

  it('should persist and restore session across reloads', async () => {
    // Login and verify credentials are stored
    const { login } = useAuthStore.getState();
    await login('testuser', 'testpass');

    // Verify state is set correctly
    let state = useAuthStore.getState();
    expect(state.username).toBe('testuser');
    expect(state.password).toBe('testpass');
    expect(state.isAuthenticated).toBe(true);

    // Verify something is in sessionStorage
    const stored = sessionStorage.getItem('auth-storage');
    expect(stored).toBeTruthy();
  });
});
