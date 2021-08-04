import { Switch, Route } from "react-router-dom";
import { ProtectedRoute } from "./auth";
import { Nav } from "./components";
import { LoginForm } from "./login";

const App = () => {
  return (
    <>
      <Nav />
      <Switch>
        <Route path="/login">
          <LoginForm />
        </Route>
        <ProtectedRoute path="/">
          {null}
        </ProtectedRoute>
      </Switch>
    </>
  );
};

export default App;
