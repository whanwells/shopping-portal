import Container from "react-bootstrap/Container";
import { QueryClient, QueryClientProvider } from "react-query";
import { Switch, Route } from "react-router-dom";
import { Cart } from "./cart";
import { Navigation } from "./components";
import { OrderList, OrderDetail } from "./order";
import { ProtectedRoute } from "./router";
import { LoginForm, Logout } from "./session";
import { ProductListing } from "./shop";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
});

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <Navigation />
      <Container>
        <Switch>
          <Route exact path="/login" component={LoginForm} />
          <ProtectedRoute exact path="/" component={ProductListing} />
          <ProtectedRoute exact path="/cart" component={Cart} />
          <ProtectedRoute
            exact
            path="/orders/:orderId"
            component={OrderDetail}
          />
          <ProtectedRoute exact path="/orders" component={OrderList} />
          <ProtectedRoute exact path="/logout" component={Logout} />
        </Switch>
      </Container>
    </QueryClientProvider>
  );
};

export default App;
