
import './NotFoundPage.css';

const NotFoundPage = () => {
  return (
    <div className="not-found-container">
      <div className="not-found-content">
        <h1 className="not-found-heading">404</h1>
        <p className="not-found-text">Oops! The page you are looking for does not exist.</p>
        <div className="not-found-animation">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            className="not-found-svg"
          >
            <path
              fill="#FF7F50"
              d="M459.43 52.57C411.11 4.25 350.12-.09 304 45.86L256 97.86l-48-48-52.57 52.57c-48.31 48.31-52.56 109.3-4.25 157.57L91.43 457.73C95.43 461.73 100.69 464 106 464h300c5.31 0 10.57-2.27 14.57-6.27l93.14-93.14c48.31-48.26 44.06-109.26-4.25-157.57zM256 434c-94.13 0-170-75.87-170-170S161.87 94 256 94s170 75.87 170 170-75.87 170-170 170z"
            />
            <path
              fill="#FFF"
              d="M339.48 242.07L282 184.59l-57.48 57.48c-5.86 5.86-5.86 15.35 0 21.21 5.86 5.86 15.35 5.86 21.21 0l36.27-36.27 36.27 36.27c5.86 5.86 15.35 5.86 21.21 0 5.86-5.86 5.86-15.35 0-21.21z"
            />
          </svg>
        </div>
      </div>
    </div>
  );
};

export default NotFoundPage;