import type { VFC } from "react";
import { Link } from "react-router-dom";

const categories = ["Consoles", "Games", "Accessories"] as const;

export const CategorySelection: VFC = () => (
  <>
    <h1>Shop Products</h1>
    <ul>
      {categories.map((category) => (
        <li key={category}>
          <Link to={`/shop/${category.toLowerCase()}`}>{category}</Link>
        </li>
      ))}
    </ul>
  </>
);
