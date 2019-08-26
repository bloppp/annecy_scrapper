import { Movie } from './Movie';
import { SessionDto } from '../api/model/session.dto';
import { LocationDto } from '../api/model/location.dto';

export class Session {
    id: number;
    movie: Movie;
    location: LocationDto;
    startDate: Date;
    endDate: Date;
    visible: boolean = true;
    row: number;
    startColumn: number;
    endColumn: number;


    public constructor(dto : SessionDto) {
        this.id = dto.id;
        this.movie = dto.movie != null ? new Movie(dto.movie) : null;
        this.location = new LocationDto(dto.location);
        this.startDate = new Date(dto.startTimestamp);
        this.endDate = new Date(dto.endTimestamp);
    }

    getRow() {
        return this.startDate.getDay() * 2;
    }


    getStartColumn() {
        return (this.startDate.getHours() - 8) * 60 + this.startDate.getMinutes();
    }

    getEndColumn() {
        return this.getStartColumn() + Math.ceil((this.endDate.getTime() - this.startDate.getTime()) / 1000 / 60);
    }

    hide() {
        this.visible = false;
    }

    show() {
        this.visible = true;
    }
  };