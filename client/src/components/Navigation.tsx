import { Navbar, Nav } from "react-bootstrap";
import { useToken } from "../auth";

export const Navigation = () => {
  const { token, setToken } = useToken();

  function handleLogout() {
    setToken(null);
  }

  return (
    <Navbar bg="dark" variant="dark" expand="lg" sticky="top" className="mb-4">
      <Navbar.Brand href="#">Shopping Portal</Navbar.Brand>
      {token && (
        <Nav>
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="#" onClick={handleLogout}>Logout</Nav.Link>
        </Nav>
      )}
    </Navbar>
  );
};
