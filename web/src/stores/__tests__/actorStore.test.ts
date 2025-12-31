import { describe, it, expect, beforeEach } from 'vitest';
import { useActorStore } from '../actorStore';

describe('actorStore', () => {
  beforeEach(() => {
    // Reset store state before each test
    useActorStore.setState({
      leftActorId: null,
      rightActorId: null,
    });
  });

  it('should have initial state with null actor IDs', () => {
    const state = useActorStore.getState();
    expect(state.leftActorId).toBeNull();
    expect(state.rightActorId).toBeNull();
  });

  it('should set left actor ID', () => {
    const { setLeftActor } = useActorStore.getState();
    setLeftActor(123);

    const state = useActorStore.getState();
    expect(state.leftActorId).toBe(123);
    expect(state.rightActorId).toBeNull();
  });

  it('should set right actor ID', () => {
    const { setRightActor } = useActorStore.getState();
    setRightActor(456);

    const state = useActorStore.getState();
    expect(state.leftActorId).toBeNull();
    expect(state.rightActorId).toBe(456);
  });

  it('should set both actor IDs independently', () => {
    const { setLeftActor, setRightActor } = useActorStore.getState();

    setLeftActor(31); // Tom Hanks
    setRightActor(85); // Johnny Depp

    const state = useActorStore.getState();
    expect(state.leftActorId).toBe(31);
    expect(state.rightActorId).toBe(85);
  });

  it('should update left actor ID', () => {
    const { setLeftActor } = useActorStore.getState();

    setLeftActor(123);
    expect(useActorStore.getState().leftActorId).toBe(123);

    setLeftActor(456);
    expect(useActorStore.getState().leftActorId).toBe(456);
  });

  it('should update right actor ID', () => {
    const { setRightActor } = useActorStore.getState();

    setRightActor(123);
    expect(useActorStore.getState().rightActorId).toBe(123);

    setRightActor(789);
    expect(useActorStore.getState().rightActorId).toBe(789);
  });

  it('should reset both actor IDs to null', () => {
    const { setLeftActor, setRightActor, reset } = useActorStore.getState();

    // Set both actors
    setLeftActor(31);
    setRightActor(85);
    expect(useActorStore.getState().leftActorId).toBe(31);
    expect(useActorStore.getState().rightActorId).toBe(85);

    // Reset
    reset();
    const state = useActorStore.getState();
    expect(state.leftActorId).toBeNull();
    expect(state.rightActorId).toBeNull();
  });

  it('should allow setting actor IDs to null', () => {
    const { setLeftActor, setRightActor } = useActorStore.getState();

    // Set actors
    setLeftActor(123);
    setRightActor(456);

    // Clear actors by setting to null
    setLeftActor(null);
    setRightActor(null);

    const state = useActorStore.getState();
    expect(state.leftActorId).toBeNull();
    expect(state.rightActorId).toBeNull();
  });
});
