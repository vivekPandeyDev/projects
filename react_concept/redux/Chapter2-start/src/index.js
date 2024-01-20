import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { applyMiddleware, combineReducers, createStore } from "redux";
import { accountReducer } from "./reducers/account";
import logger from "redux-logger";
import { Provider } from "react-redux";
import { bonusReducer } from "./reducers/bonus";
import thunk from "redux-thunk";

const store = createStore(
  combineReducers({
    account: accountReducer,
    bonus: bonusReducer,
  }),
  applyMiddleware(logger, thunk)
);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
);

reportWebVitals();
