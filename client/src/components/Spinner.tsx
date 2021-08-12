import type { VFC } from "react";
import { Spinner as BsSpinner } from "react-bootstrap";

export const Spinner: VFC = () => (
  <div className="d-flex justify-content-center">
    <BsSpinner
      animation="border"
      role="status"
      style={{ width: "3em", height: "3em" }}
    >
      <span className="sr-only">Loading...</span>
    </BsSpinner>
  </div>
);
