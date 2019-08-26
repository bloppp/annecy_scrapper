import { CategoryDto } from './category.dto';

export class MovieDto {
    title : string;
    category : CategoryDto;
    year : number;
}