import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import apiClient from '@/services/api/client';

interface AuthState {
  username: string | null;
  password: string | null;
  isAuthenticated: boolean;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      username: null,
      password: null,
      isAuthenticated: false,

      login: async (username: string, password: string) => {
        // Test credentials with a simple API call
        try {
          // Temporarily set credentials to test them
          set({ username, password });

          // Make a test API call to verify credentials
          await apiClient.get('/search/person/test');

          // If successful, mark as authenticated
          set({ isAuthenticated: true });
        } catch (error) {
          // If failed, clear credentials
          set({ username: null, password: null, isAuthenticated: false });
          throw new Error('Invalid credentials');
        }
      },

      logout: () => {
        set({ username: null, password: null, isAuthenticated: false });
      },
    }),
    {
      name: 'auth-storage',
      // Only persist username and isAuthenticated, not password for security
      partialize: (state) => ({
        username: state.username,
        isAuthenticated: state.isAuthenticated,
      }),
    }
  )
);
