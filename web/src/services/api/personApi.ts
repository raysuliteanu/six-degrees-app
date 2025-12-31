import apiClient from './client';
import type {
  PersonSearchResult,
  PersonDetails,
  PersonCombinedCredits,
} from '@/types/api';

export const personApi = {
  searchByName: async (name: string): Promise<PersonSearchResult> => {
    const response = await apiClient.get<PersonSearchResult>(
      `/search/person/${encodeURIComponent(name)}`
    );
    return response.data;
  },

  getById: async (id: number): Promise<PersonDetails> => {
    const response = await apiClient.get<PersonDetails>(`/person/${id}`);
    return response.data;
  },

  getCredits: async (id: number): Promise<PersonCombinedCredits> => {
    const response = await apiClient.get<PersonCombinedCredits>(
      `/person/${id}/credits`
    );
    return response.data;
  },
};
