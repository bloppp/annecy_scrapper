import { MovieDto } from './movie.dto';

export class SessionDto {
    id: number;
    movie: MovieDto;
    location: string;
    startTimestamp: number;
    endTimestamp: number;
}