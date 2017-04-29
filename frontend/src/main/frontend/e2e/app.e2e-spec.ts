import { SpringOauth2ExamplePage } from './app.po';

describe('spring-oauth2-example App', () => {
  let page: SpringOauth2ExamplePage;

  beforeEach(() => {
    page = new SpringOauth2ExamplePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
