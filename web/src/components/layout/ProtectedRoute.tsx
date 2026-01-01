import { Navigate } from 'react-router-dom';
import { useAuthStore } from '@/stores/authStore';

interface ProtectedRouteProps {
  children: React.ReactNode;
}

export function ProtectedRoute({ children }: ProtectedRouteProps) {
  const isAuthenticated = useAuthStore((state) => state.isAuthenticated);
  const validateSession = useAuthStore((state) => state.validateSession);

  // Validate session on route access
  if (isAuthenticated && !validateSession()) {
    // Session expired, will be redirected by the next check
  }

  if (!useAuthStore.getState().isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
}
