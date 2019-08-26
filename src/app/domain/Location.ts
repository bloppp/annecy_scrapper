import { LocationDto } from '../api/model/location.dto';

export class Location {

    name: string;
    id : string;
    label: string;
    
    public constructor(dto: LocationDto) {
      this.name = dto.name;
      this.id = this.name;
      this.label = this.name;
    }
  };