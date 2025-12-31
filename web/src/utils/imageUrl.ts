const TMDB_IMAGE_BASE_URL =
  import.meta.env.VITE_TMDB_IMAGE_BASE_URL || 'https://image.tmdb.org/t/p';

export type ImageSize = 'w92' | 'w185' | 'w500' | 'original';

export function getImageUrl(
  path: string | null,
  size: ImageSize = 'w185'
): string {
  if (!path) {
    return '/placeholder-avatar.png';
  }
  return `${TMDB_IMAGE_BASE_URL}/${size}${path}`;
}
