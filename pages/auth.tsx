import SigninForm from '@/components/Auth/SigninForm';
import SignupForm from '@/components/Auth/SignupForm';
import { useState } from 'react';

const AuthenticationPage: React.FC = () => {
  const [isSigningUp, setIsSigningUp] = useState(true);

  const toggleForm = () => {
    setIsSigningUp((prevIsSigningUp) => !prevIsSigningUp);
  };

  return (
    <div>
      <h1>{isSigningUp ? 'Sign Up' : 'Sign In'}</h1>
      {isSigningUp ? <SignupForm /> : <SigninForm />}
      <button onClick={toggleForm}>
        {isSigningUp ? 'Already have an account? Sign In' : 'Don\'t have an account? Sign Up'}
      </button>
    </div>
  );
};

export default AuthenticationPage;
