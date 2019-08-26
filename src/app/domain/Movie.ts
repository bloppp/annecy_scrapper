import { Category } from './Category';
import { MovieDto } from '../api/model/movie.dto';

export class Movie {
    title: string;
    category: Category;


    public constructor(dto : MovieDto) {
        this.title = dto.title;
        this.category = dto.category != null ? new Category(dto.category) : null;
    }
  };