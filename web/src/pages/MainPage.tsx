import { useAuthStore } from '@/stores/authStore';
import { Button } from '@/components/ui/button';

export function MainPage() {
  const { username, logout } = useAuthStore();

  return (
    <div className="min-h-screen bg-background p-8">
      <div className="mx-auto max-w-7xl">
        <div className="mb-8 flex items-center justify-between">
          <div>
            <h1 className="text-4xl font-bold tracking-tight">Six Degrees</h1>
            <p className="mt-2 text-muted-foreground">
              Welcome, {username}!
            </p>
          </div>
          <Button onClick={logout} variant="outline">
            Logout
          </Button>
        </div>

        <div className="rounded-lg border bg-card p-8 text-center">
          <h2 className="text-2xl font-semibold">Coming Soon</h2>
          <p className="mt-2 text-muted-foreground">
            Actor search and connection graph will be implemented in the next phases.
          </p>
        </div>
      </div>
    </div>
  );
}
