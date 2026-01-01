import { useState, useRef, useEffect } from 'react';
import { useQuery } from '@tanstack/react-query';
import { personApi } from '@/services/api/personApi';
import { useDebounce } from '@/hooks/useDebounce';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { getImageUrl } from '@/utils/imageUrl';
import type { Person } from '@/types/api';

interface ActorSearchInputProps {
  side: 'left' | 'right';
  label: string;
  onActorSelected: (actorId: number) => void;
  onBlur?: () => void;
}

export function ActorSearchInput({
  side,
  label,
  onActorSelected,
  onBlur,
}: ActorSearchInputProps) {
  const [searchTerm, setSearchTerm] = useState('');
  const [isOpen, setIsOpen] = useState(false);
  const [selectedActor, setSelectedActor] = useState<Person | null>(null);
  const debouncedSearch = useDebounce(searchTerm, 300);
  const inputRef = useRef<HTMLInputElement>(null);
  const dropdownRef = useRef<HTMLDivElement>(null);

  const { data: searchResults, isLoading, isError, error } = useQuery({
    queryKey: ['person-search', debouncedSearch],
    queryFn: () => personApi.searchByName(debouncedSearch),
    enabled: debouncedSearch.length >= 2,
    staleTime: 5 * 60 * 1000, // 5 minutes
  });

  // Close dropdown when clicking outside
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node) &&
        !inputRef.current?.contains(event.target as Node)
      ) {
        setIsOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setSearchTerm(value);
    setIsOpen(value.length >= 2);
    if (value.length === 0) {
      setSelectedActor(null);
      onActorSelected(0);
    }
  };

  const handleActorSelect = (actor: Person) => {
    setSelectedActor(actor);
    setSearchTerm(actor.name);
    setIsOpen(false);
    onActorSelected(actor.id);
  };

  const handleBlur = () => {
    // Delay to allow click on dropdown
    setTimeout(() => {
      if (onBlur && selectedActor) {
        onBlur();
      }
    }, 200);
  };

  return (
    <div className="relative">
      <Label htmlFor={`actor-search-${side}`}>{label}</Label>
      <Input
        ref={inputRef}
        id={`actor-search-${side}`}
        type="text"
        role="combobox"
        aria-expanded={isOpen}
        aria-autocomplete="list"
        aria-controls={`actor-listbox-${side}`}
        aria-label={`Search for ${side} actor`}
        placeholder="Search for an actor..."
        value={searchTerm}
        onChange={handleInputChange}
        onFocus={() => searchTerm.length >= 2 && setIsOpen(true)}
        onBlur={handleBlur}
        data-testid={`actor-search-${side}`}
        className="mt-1"
      />

      {isOpen && (
        <div
          ref={dropdownRef}
          id={`actor-listbox-${side}`}
          role="listbox"
          aria-label={`Search results for ${side} actor`}
          className="absolute z-10 mt-1 w-full rounded-md border bg-popover shadow-lg"
        >
          <div className="max-h-60 overflow-auto p-1">
            {isLoading && (
              <div className="p-3 text-sm text-muted-foreground" role="status">
                Searching...
              </div>
            )}

            {isError && (
              <div className="p-3 text-sm text-destructive" role="alert">
                {error instanceof Error &&
                error.message.includes('Session expired')
                  ? 'Session expired. Please log in again.'
                  : error instanceof Error &&
                    (error.message.includes('401') ||
                      error.message.includes('Unauthorized'))
                  ? 'Authentication failed. Please log in again.'
                  : error instanceof Error &&
                    (error.message.includes('Network') ||
                      error.message.includes('timeout'))
                  ? 'Network error. Please check your connection.'
                  : 'Failed to search for actors. Please try again.'}
              </div>
            )}

            {!isLoading && !isError && searchResults?.results?.length === 0 && (
              <div className="p-3 text-sm text-muted-foreground" role="status">
                No actors found
              </div>
            )}

            {!isLoading &&
              searchResults?.results?.map((actor) => (
                <button
                  key={actor.id}
                  type="button"
                  role="option"
                  aria-selected={selectedActor?.id === actor.id}
                  onClick={() => handleActorSelect(actor)}
                  className="flex w-full items-center gap-3 rounded-sm p-2 hover:bg-accent"
                  data-testid={`actor-result-${actor.id}`}
                >
                  <img
                    src={getImageUrl(actor.profilePath, 'w92')}
                    alt={`${actor.name} profile`}
                    className="h-12 w-12 rounded-full object-cover"
                    onError={(e) => {
                      (e.target as HTMLImageElement).src =
                        '/placeholder-avatar.svg';
                    }}
                  />
                  <div className="flex-1 text-left">
                    <div className="font-medium">{actor.name}</div>
                    <div className="text-sm text-muted-foreground">
                      {actor.knownForDepartment}
                    </div>
                  </div>
                </button>
              ))}
          </div>
        </div>
      )}
    </div>
  );
}
