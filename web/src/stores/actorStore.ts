import { create } from 'zustand';

interface ActorState {
  leftActorId: number | null;
  rightActorId: number | null;
  setLeftActor: (id: number | null) => void;
  setRightActor: (id: number | null) => void;
  reset: () => void;
}

export const useActorStore = create<ActorState>((set) => ({
  leftActorId: null,
  rightActorId: null,
  setLeftActor: (id) => set({ leftActorId: id }),
  setRightActor: (id) => set({ rightActorId: id }),
  reset: () => set({ leftActorId: null, rightActorId: null }),
}));
