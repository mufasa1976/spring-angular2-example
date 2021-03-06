import {Component, Input, OnInit} from '@angular/core';
import {Hero} from "../hero";
import {HeroService} from "../hero.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.scss']
})
export class HeroDetailComponent implements OnInit {

  @Input() hero: Hero;

  constructor(
    private heroService: HeroService,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.activatedRoute.params
      .switchMap((params: Params) => this.heroService.getHero(params['reference']))
      .subscribe(hero => this.hero = hero);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.heroService.update(this.hero)
      .then(() => this.goBack());
  }

}
