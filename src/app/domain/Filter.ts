export class Filter {

    location: string[];
    category: string[];
    
    public constructor(location: string[], category: string[]) {
        this.location = location;
        this.category = category;
    }
  };