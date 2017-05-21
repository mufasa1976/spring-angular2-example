import { SpringAngular2ExamplePage } from './app.po';

describe('spring-angular2-example App', () => {
  let page: SpringAngular2ExamplePage;

  beforeEach(() => {
    page = new SpringAngular2ExamplePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
