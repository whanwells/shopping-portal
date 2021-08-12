export type Product = {
  id: number;
  name: string;
  category: string;
  msrp: number;
  stocked: boolean;
};

export type CartItem = {
  product: Omit<Product, "stocked">;
  quantity: number;
};
