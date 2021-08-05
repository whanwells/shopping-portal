interface RequestOptions extends RequestInit {
  token?: string;
}

class RequestError extends Error {
  readonly status: number;

  constructor(status: number, message: string) {
    super(message);
    this.name = "RequestError";
    this.status = status;
  }
}

export const request = async (path: string, options: RequestOptions = {}) => {
  const { token, ...config } = options;
  const headers: HeadersInit = {};

  if (token !== undefined) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const response = await fetch(path, config);

  if (!response.ok) {
    const message = await response.text();
    throw new RequestError(response.status, message);
  }

  return response;
};

request.get = (path: string, options: RequestOptions = {}) => {
  options.method = "GET";
  return request(path, options);
};

request.post = (path: string, body: BodyInit, options: RequestOptions = {}) => {
  options.method = "POST";
  options.body = body;
  return request(path, options);
};
