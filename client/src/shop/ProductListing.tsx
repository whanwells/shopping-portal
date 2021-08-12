import type { VFC } from "react";
import { useParams } from "react-router";
import { useQuery } from "react-query";
import { Spinner } from "../components";
import { request } from "../request";
import { useToken } from "../token";
import type { Product } from "../types";
import { ProductButton } from "./ProductButton";

type ProductRouteParams = {
  category: string;
};

function titleCase(str: string) {
  return str[0].toUpperCase() + str.substr(1);
}

export const ProductListing: VFC = () => {
  const { token } = useToken();
  const { category } = useParams<ProductRouteParams>();

  const { data, isLoading } = useQuery(["products", category], async () => {
    const response = await request.get(`/api/products?category=${category}`, {
      token,
    });
    return response.json();
  });

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <h1>{titleCase(category)}</h1>
      <ul>
        {data[0].products.map(({ id, name, msrp, stocked }: Product) => (
          <li key={id}>
            <div>{name}</div>
            <div>${msrp}</div>
            <div>
              {stocked ? <ProductButton id={id} /> : <div>Out of Stock</div>}
            </div>
          </li>
        ))}
      </ul>
    </>
  );
};
