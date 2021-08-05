import { QueryClient, QueryClientProvider } from "react-query";
import { Switch, Route } from "react-router-dom";
import { Navigation, ProtectedRoute } from "./router";
import { LoginForm } from "./login";

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
      <Switch>
        <Route path="/login">
          <LoginForm />
        </Route>
        <ProtectedRoute path="/">{null}</ProtectedRoute>
      </Switch>
    </QueryClientProvider>
  );
};

export default App;
