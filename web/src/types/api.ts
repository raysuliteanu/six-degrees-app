// Person types
export interface Person {
  adult: boolean;
  gender: number;
  id: number;
  knownForDepartment: string;
  name: string;
  originalName: string;
  popularity: number;
  profilePath: string | null;
  knownFor: KnownFor[];
}

export interface PersonDetails {
  adult: boolean;
  alsoKnownAs: string[];
  biography: string | null;
  birthday: string | null;
  deathday: string | null;
  gender: number;
  homepage: string | null;
  id: number;
  imdbId: string | null;
  knownForDepartment: string | null;
  name: string;
  placeOfBirth: string | null;
  popularity: number;
  profilePath: string | null;
}

export interface PersonSearchResult {
  page: number;
  results: Person[];
  totalPages: number;
  totalResults: number;
}

export interface PersonCombinedCredits {
  id: number;
  cast: PersonCombinedCreditsCast[];
  crew: PersonCombinedCreditsCrew[];
}

export interface PersonCombinedCreditsCast {
  id: number;
  title: string;
  overview: string | null;
  releaseDate: string | null;
  posterPath: string | null;
  backdropPath: string | null;
  character: string | null;
  creditId: string;
  order: number;
  mediaType: string;
  voteAverage: number;
  voteCount: number;
  popularity: number;
}

export interface PersonCombinedCreditsCrew {
  id: number;
  title: string;
  overview: string | null;
  releaseDate: string | null;
  posterPath: string | null;
  backdropPath: string | null;
  department: string;
  job: string;
  creditId: string;
  mediaType: string;
}

export interface KnownFor {
  id: number;
  title: string;
  overview: string | null;
  releaseDate: string | null;
  posterPath: string | null;
  backdropPath: string | null;
  mediaType: string;
  genreIds: number[];
  popularity: number;
  voteAverage: number;
  voteCount: number;
}

// Movie types
export interface Movie {
  adult: boolean;
  backdropPath: string | null;
  genreIds: number[];
  id: number;
  originalLanguage: string;
  originalTitle: string;
  overview: string;
  popularity: number;
  posterPath: string | null;
  releaseDate: string;
  title: string;
  video: boolean;
  voteAverage: number;
  voteCount: number;
}

export interface MovieDetails {
  id: number;
  title: string;
  originalTitle: string;
  overview: string | null;
  releaseDate: string | null;
  originalLanguage: string;
  backdropPath: string | null;
  posterPath: string | null;
  homepage: string | null;
  budget: number;
  revenue: number;
  runtime: number;
  status: string;
  tagline: string | null;
  imdbId: string | null;
  popularity: number;
  voteAverage: number;
  voteCount: number;
  adult: boolean;
  video: boolean;
}

export interface MovieSearchResult {
  page: number;
  results: Movie[];
  totalPages: number;
  totalResults: number;
}

// Connection types
export interface ConnectionNode {
  id: string;
  type: 'actor' | 'movie';
  name: string;
  imageUrl: string | null;
  metadata: Record<string, any>;
}

export interface ConnectionEdge {
  from: string;
  to: string;
  label: string | null;
}

export interface ConnectionPath {
  nodes: ConnectionNode[];
  edges: ConnectionEdge[];
  degree: number;
}
