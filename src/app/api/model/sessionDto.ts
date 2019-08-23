import { MovieDto } from './movieDto';

export class SessionDto {
    id: number;
    movie: MovieDto;
    location: string;
    startTimestamp: number;
    endTimestamp: number;
}