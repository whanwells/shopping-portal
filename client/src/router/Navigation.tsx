import { Link } from "react-router-dom";
import { useToken } from "../token";

export const Navigation = () => {
  const { token, setToken } = useToken();

  function handleLogout() {
    setToken(null);
  }

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
            <a href="#" onClick={handleLogout}>
              Logout
            </a>
          </li>
        </ul>
      )}
    </nav>
  );
};
