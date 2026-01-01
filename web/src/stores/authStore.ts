import { create } from 'zustand';
import { persist, createJSONStorage } from 'zustand/middleware';

const SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes

interface AuthState {
  username: string | null;
  password: string | null;
  isAuthenticated: boolean;
  lastActivity: number | null;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
  validateSession: () => boolean;
  refreshActivity: () => void;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set, get) => ({
      username: null,
      password: null,
      isAuthenticated: false,
      lastActivity: null,

      login: async (username: string, password: string) => {
        // Set credentials immediately - no test call
        // First actual API call will validate credentials
        // If it fails, the response interceptor will handle logout
        set({
          username,
          password,
          isAuthenticated: true,
          lastActivity: Date.now(),
        });
      },

      logout: () => {
        set({
          username: null,
          password: null,
          isAuthenticated: false,
          lastActivity: null,
        });
      },

      validateSession: () => {
        const state = get();
        if (!state.isAuthenticated) return false;
        if (!state.lastActivity) return false;

        const elapsed = Date.now() - state.lastActivity;
        if (elapsed > SESSION_TIMEOUT) {
          get().logout();
          return false;
        }

        return true;
      },

      refreshActivity: () => {
        set({ lastActivity: Date.now() });
      },
    }),
    {
      name: 'auth-storage',
      storage: createJSONStorage(() => ({
        getItem: (name: string) => {
          const str = sessionStorage.getItem(name);
          if (!str) return null;

          try {
            const parsed = JSON.parse(str);

            // Decode credentials if they exist in storage
            if (parsed.state?.encodedCreds) {
              const decoded = atob(parsed.state.encodedCreds);
              const [username, password] = decoded.split(':');
              return JSON.stringify({
                state: {
                  username,
                  password,
                  isAuthenticated: parsed.state.isAuthenticated,
                  lastActivity: parsed.state.lastActivity,
                },
              });
            }

            return str;
          } catch {
            return str;
          }
        },

        setItem: (name: string, value: string) => {
          try {
            const parsed = JSON.parse(value);

            // Encode credentials before storing
            if (parsed.state?.username && parsed.state?.password) {
              const encodedCreds = btoa(
                `${parsed.state.username}:${parsed.state.password}`
              );
              const toStore = {
                state: {
                  isAuthenticated: parsed.state.isAuthenticated,
                  lastActivity: parsed.state.lastActivity,
                  encodedCreds,
                },
              };
              sessionStorage.setItem(name, JSON.stringify(toStore));
            } else {
              sessionStorage.setItem(name, value);
            }
          } catch {
            sessionStorage.setItem(name, value);
          }
        },

        removeItem: (name: string) => {
          sessionStorage.removeItem(name);
        },
      })),
    }
  )
);
