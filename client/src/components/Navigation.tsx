import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
import { useToken } from "../token";

const ROUTES = {
  "/": "Shop",
  "/cart": "Cart",
  "/orders": "Orders",
  "/logout": "Logout",
};

export const Navigation = () => {
  const { token } = useToken();

  return (
    <Navbar
      bg="dark"
      variant="dark"
      expand="lg"
      collapseOnSelect
      className="mb-3"
    >
      <Container>
        <Navbar.Brand as={Link} to="/">
          Shopping Portal
        </Navbar.Brand>
        {token && (
          <>
            <Navbar.Toggle aria-controls="navbar-nav" />
            <Navbar.Collapse id="navbar-nav">
              <Nav className="ml-auto">
                {Object.entries(ROUTES).map(([route, label]) => (
                  <Nav.Link as={Link} to={route} href={route}>
                    {label}
                  </Nav.Link>
                ))}
              </Nav>
            </Navbar.Collapse>
          </>
        )}
      </Container>
    </Navbar>
  );
};
