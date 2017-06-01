import {Injectable} from "@angular/core";
import {Hero} from "./hero";
import {Headers, Http} from "@angular/http";
import "rxjs/add/operator/toPromise";

@Injectable()
export class HeroService {

  private heroesUrl = '/api/heroes'; // URL to web api
  private headers = new Headers({'Content-Type': 'application/json'});

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

  getHero(reference: string): Promise<Hero> {
    const url = `${this.heroesUrl}/${reference}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(HeroService.handleError);
  }

  update(hero: Hero): Promise<Hero> {
    return this.http
      .put(hero._links.self.href, JSON.stringify(hero), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(HeroService.handleError);
  }

  create(name: string): Promise<Hero> {
    return this.http
      .post(this.heroesUrl, JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(HeroService.handleError);
  }

  delete(reference: string): Promise<void> {
    const url = `${this.heroesUrl}/${reference}`;
    return this.http.delete(url)
      .toPromise()
      .then(() => null)
      .catch(HeroService.handleError);
  }

}
