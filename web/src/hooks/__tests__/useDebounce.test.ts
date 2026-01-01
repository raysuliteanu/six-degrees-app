import { renderHook, waitFor } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { useDebounce } from '../useDebounce';

describe('useDebounce', () => {
  it('should debounce value changes', async () => {
    const { result, rerender } = renderHook(
      ({ value, delay }) => useDebounce(value, delay),
      {
        initialProps: { value: 'initial', delay: 300 },
      }
    );

    expect(result.current).toBe('initial');

    // Update the value
    rerender({ value: 'updated', delay: 300 });

    // Value should not update immediately
    expect(result.current).toBe('initial');

    // Wait for debounce delay
    await waitFor(
      () => {
        expect(result.current).toBe('updated');
      },
      { timeout: 500 }
    );
  });

  it('should use default delay of 300ms', async () => {
    const { result, rerender } = renderHook(
      (value) => useDebounce(value),
      {
        initialProps: 'test',
      }
    );

    rerender('changed');

    expect(result.current).toBe('test');

    await waitFor(
      () => {
        expect(result.current).toBe('changed');
      },
      { timeout: 500 }
    );
  });
});
