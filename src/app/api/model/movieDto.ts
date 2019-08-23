import { CategoryDto } from './categoryDto';

export class MovieDto {
    title : string;
    category : CategoryDto;
    year : number;
}