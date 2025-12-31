import apiClient from './client';
import type { ConnectionPath } from '@/types/api';

export const connectionApi = {
  findConnection: async (
    actor1Id: number,
    actor2Id: number,
    maxDegrees: number = 6
  ): Promise<ConnectionPath[]> => {
    const response = await apiClient.get<ConnectionPath[]>(
      `/connection/${actor1Id}/${actor2Id}`,
      {
        params: { maxDegrees },
      }
    );
    return response.data;
  },
};
