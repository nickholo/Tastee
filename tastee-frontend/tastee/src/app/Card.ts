export class Card {
  constructor(
    public cardId: number,
    public title: string,
    public description: string,
    public imgUrl?: string,
    public author?: string
  ) {}

  static fromJson(json: any): Card {
    return new Card(json.cardId ?? json.id, json.title, json.description, json.imgUrl, json.author);
  }
}