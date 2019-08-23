import { CategoryDto } from '../api/model/categoryDto';

export class Category {
    name: string;
    id : string;
    label: string;
    color: string;
    
    public constructor(dto: CategoryDto) {
        this.name = dto.name;
        this.label = dto.name;
        this.id = dto.name;
        this.color = dto.color;
    }
  };