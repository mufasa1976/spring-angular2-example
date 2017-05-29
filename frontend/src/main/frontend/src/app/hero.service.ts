import {Injectable} from "@angular/core";
import {Hero} from "./hero";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";

@Injectable()
export class HeroService {

  private heroesUrl = '/api/heroes'; // URL to web api

  constructor(private http: Http) {
  }

  getHeroes(): Promise<Hero[]> {
    return this.http.get(this.heroesUrl)
      .toPromise()
      .then(response => {
        console.log(response.json());
        return response.json()._embedded.heroes as Hero[];
      })
      .catch(HeroService.handleError);
  }

  private static handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getHero(id: number): Promise<Hero> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(HeroService.handleError);
  }

}