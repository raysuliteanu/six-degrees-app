import { describe, it, expect } from 'vitest';
import { getImageUrl } from '../imageUrl';

describe('getImageUrl', () => {
  it('should return TMDB URL with correct size', () => {
    const result = getImageUrl('/abc123.jpg', 'w185');
    expect(result).toBe('https://image.tmdb.org/t/p/w185/abc123.jpg');
  });

  it('should use default size of w185', () => {
    const result = getImageUrl('/abc123.jpg');
    expect(result).toBe('https://image.tmdb.org/t/p/w185/abc123.jpg');
  });

  it('should return placeholder for null path', () => {
    const result = getImageUrl(null);
    expect(result).toBe('/placeholder-avatar.png');
  });

  it('should support different image sizes', () => {
    expect(getImageUrl('/test.jpg', 'w92')).toContain('/w92/');
    expect(getImageUrl('/test.jpg', 'w500')).toContain('/w500/');
    expect(getImageUrl('/test.jpg', 'original')).toContain('/original/');
  });
});
