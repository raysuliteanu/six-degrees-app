import { LoginForm } from '@/components/auth/LoginForm';

export function LoginPage() {
  return (
    <div className="flex min-h-screen items-center justify-center bg-background p-4">
      <div className="w-full max-w-md">
        <div className="mb-8 text-center">
          <h1 className="text-4xl font-bold tracking-tight">Six Degrees</h1>
          <p className="mt-2 text-muted-foreground">
            Find connections between actors through their movie collaborations
          </p>
        </div>
        <LoginForm />
      </div>
    </div>
  );
}
