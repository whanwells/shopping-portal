import { Link } from "react-router-dom";
import { useToken } from "../token";

export const Navigation = () => {
  const { token } = useToken();

  return (
    <nav>
      <div>Shopping Portal</div>
      {token && (
        <ul>
          <li>
            <Link to="/">Products</Link>
          </li>
          <li>
            <Link to="/cart">Cart</Link>
          </li>
          <li>
            <Link to="/logout">Logout</Link>
          </li>
        </ul>
      )}
    </nav>
  );
};
