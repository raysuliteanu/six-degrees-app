import { useCallback } from 'react';
import { useQueryClient } from '@tanstack/react-query';
import { useAuthStore } from '@/stores/authStore';
import { useActorStore } from '@/stores/actorStore';
import { personApi } from '@/services/api/personApi';
import { Button } from '@/components/ui/button';
import { ActorSearchInput } from '@/components/actor/ActorSearchInput';
import { ActorCard } from '@/components/actor/ActorCard';

export function MainPage() {
  const { username, logout } = useAuthStore();
  const { leftActorId, rightActorId, setLeftActor, setRightActor } =
    useActorStore();
  const queryClient = useQueryClient();

  const handleLeftActorSelected = useCallback(
    (actorId: number) => {
      setLeftActor(actorId || null);
    },
    [setLeftActor]
  );

  const handleRightActorSelected = useCallback(
    (actorId: number) => {
      setRightActor(actorId || null);
    },
    [setRightActor]
  );

  const handleLeftActorBlur = useCallback(() => {
    if (leftActorId) {
      // Prefetch actor details and credits
      queryClient.prefetchQuery({
        queryKey: ['person', leftActorId],
        queryFn: () => personApi.getById(leftActorId),
      });
      queryClient.prefetchQuery({
        queryKey: ['person-credits', leftActorId],
        queryFn: () => personApi.getCredits(leftActorId),
      });
    }
  }, [leftActorId, queryClient]);

  const handleRightActorBlur = useCallback(() => {
    if (rightActorId) {
      // Prefetch actor details and credits
      queryClient.prefetchQuery({
        queryKey: ['person', rightActorId],
        queryFn: () => personApi.getById(rightActorId),
      });
      queryClient.prefetchQuery({
        queryKey: ['person-credits', rightActorId],
        queryFn: () => personApi.getCredits(rightActorId),
      });
    }
  }, [rightActorId, queryClient]);

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <div className="border-b bg-card">
        <div className="mx-auto max-w-7xl px-4 py-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-3xl font-bold tracking-tight">
                Six Degrees
              </h1>
              <p className="mt-1 text-sm text-muted-foreground">
                Welcome, {username}!
              </p>
            </div>
            <Button onClick={logout} variant="outline">
              Logout
            </Button>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8">
        <div className="space-y-8">
          {/* Search Inputs */}
          <div className="grid gap-6 md:grid-cols-2">
            <ActorSearchInput
              side="left"
              label="Actor 1"
              onActorSelected={handleLeftActorSelected}
              onBlur={handleLeftActorBlur}
            />
            <ActorSearchInput
              side="right"
              label="Actor 2"
              onActorSelected={handleRightActorSelected}
              onBlur={handleRightActorBlur}
            />
          </div>

          {/* Actor Cards */}
          <div className="grid gap-6 md:grid-cols-2">
            <ActorCard actorId={leftActorId} />
            <ActorCard actorId={rightActorId} />
          </div>

          {/* Connection Graph Placeholder */}
          {leftActorId && rightActorId && (
            <div className="rounded-lg border bg-card p-8 text-center">
              <h2 className="text-2xl font-semibold">Connection Graph</h2>
              <p className="mt-2 text-muted-foreground">
                Graph visualization will be implemented in Phase 4
              </p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
