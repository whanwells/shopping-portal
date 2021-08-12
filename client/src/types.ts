export type Product = {
  id: number;
  name: string;
  category: string;
  msrp: number;
  stocked: boolean;
};

export type CartItem = {
  id: number;
  product: Omit<Product, "stocked">;
};

export type OrderLine = {
  id: number;
  product: Product;
};
