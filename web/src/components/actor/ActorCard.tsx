import { useQuery } from '@tanstack/react-query';
import { personApi } from '@/services/api/personApi';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { ActorCardSkeleton } from './ActorCardSkeleton';
import { getImageUrl } from '@/utils/imageUrl';
import { Calendar, MapPin, Award } from 'lucide-react';

interface ActorCardProps {
  actorId: number | null;
}

export function ActorCard({ actorId }: ActorCardProps) {
  const { data: actor, isLoading, error } = useQuery({
    queryKey: ['person', actorId],
    queryFn: () => personApi.getById(actorId!),
    enabled: !!actorId,
    staleTime: 5 * 60 * 1000, // 5 minutes
  });

  const { data: credits } = useQuery({
    queryKey: ['person-credits', actorId],
    queryFn: () => personApi.getCredits(actorId!),
    enabled: !!actorId,
    staleTime: 5 * 60 * 1000,
  });

  if (!actorId) {
    return (
      <Card className="h-full">
        <CardContent className="flex h-full items-center justify-center p-12">
          <p className="text-muted-foreground">Search for an actor to begin</p>
        </CardContent>
      </Card>
    );
  }

  if (isLoading) {
    return <ActorCardSkeleton />;
  }

  if (error) {
    return (
      <Card className="h-full">
        <CardContent className="flex h-full items-center justify-center p-12">
          <p className="text-destructive">Failed to load actor details</p>
        </CardContent>
      </Card>
    );
  }

  if (!actor) {
    return null;
  }

  const topCredits = credits?.cast?.slice(0, 5) || [];

  return (
    <Card className="h-full">
      <CardHeader>
        <div className="flex items-start gap-4">
          <img
            src={getImageUrl(actor.profilePath, 'w185')}
            alt={actor.name}
            className="h-24 w-24 rounded-full object-cover"
            onError={(e) => {
              (e.target as HTMLImageElement).src = '/placeholder-avatar.svg';
            }}
          />
          <div className="flex-1">
            <CardTitle className="text-2xl">{actor.name}</CardTitle>
            {actor.knownForDepartment && (
              <p className="mt-1 text-sm text-muted-foreground">
                {actor.knownForDepartment}
              </p>
            )}
          </div>
        </div>
      </CardHeader>

      <CardContent className="space-y-4">
        {actor.birthday && (
          <div className="flex items-center gap-2 text-sm">
            <Calendar className="h-4 w-4 text-muted-foreground" />
            <span>
              Born: {new Date(actor.birthday).toLocaleDateString()}
              {actor.deathday &&
                ` - Died: ${new Date(actor.deathday).toLocaleDateString()}`}
            </span>
          </div>
        )}

        {actor.placeOfBirth && (
          <div className="flex items-center gap-2 text-sm">
            <MapPin className="h-4 w-4 text-muted-foreground" />
            <span>{actor.placeOfBirth}</span>
          </div>
        )}

        {actor.biography && (
          <div>
            <h3 className="mb-2 font-semibold">Biography</h3>
            <p className="line-clamp-4 text-sm text-muted-foreground">
              {actor.biography}
            </p>
          </div>
        )}

        {topCredits.length > 0 && (
          <div>
            <div className="mb-2 flex items-center gap-2">
              <Award className="h-4 w-4 text-muted-foreground" />
              <h3 className="font-semibold">Notable Works</h3>
            </div>
            <ul className="space-y-1 text-sm">
              {topCredits.map((credit) => (
                <li key={credit.creditId} className="text-muted-foreground">
                  {credit.title}
                  {credit.character && ` (as ${credit.character})`}
                </li>
              ))}
            </ul>
          </div>
        )}
      </CardContent>
    </Card>
  );
}
