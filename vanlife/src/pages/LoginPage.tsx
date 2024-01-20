import { useEffect, useState } from "react";
import "./LoginPage.css";
import { LoginInput } from "../interface/props";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();

  const [formInputs, setFormInputs] = useState<LoginInput>({
    email: "",
    password: "",
  });

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (
      formInputs.email === "admin@gmail.com" &&
      formInputs.password === "admin123"
    ) {
      localStorage.setItem("user", JSON.stringify(formInputs));
      navigate("/host",{replace : true});
    }else{
      alert("invalid email or password")
    }
  };


  useEffect(()=>{
    if(localStorage.getItem("user") !== null)
      navigate("/host",{replace : true});
  })

  return (
    <main className='login-container'>
      <h1>Login Page</h1>
      <form onSubmit={handleSubmit} method='POST' className='login-form'>
        <div className='form-group'>
          <label htmlFor='email' className='form-label'>
            Email:
          </label>
          <input
            type='text'
            name='email'
            value={formInputs.email}
            className='form-input'
            placeholder='1@gmail.com'
            onChange={(e) =>
              setFormInputs({ ...formInputs, email: e.target.value })
            }
          />
        </div>
        <div className='form-group'>
          <label htmlFor='password' className='form-label'>
            Password:
          </label>
          <input
            name='password'
            type='password'
            value={formInputs.password}
            className='form-input'
            placeholder='*****'
            onChange={(e) =>
              setFormInputs({ ...formInputs, password: e.target.value })
            }
          />
        </div>
        <div className='form-group'>
          <button className='van-type rugged selected'>Submit</button>
        </div>
      </form>
    </main>
  );
};

export default LoginPage;
