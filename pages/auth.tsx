import SigninForm from '@/components/Auth/SigninForm';
import SignupForm from '@/components/Auth/SignupForm';
import {
  Button,
  Card,
  Container,
  Text
} from '@nextui-org/react';
import { useState } from 'react';

const AuthenticationPage: React.FC = () => {
  const [isSigningUp, setIsSigningUp] = useState(true);

  const toggleForm = () => {
    setIsSigningUp((prevIsSigningUp) => !prevIsSigningUp);
  };

  return (
    <Container
      display="flex"
      alignItems="center"
      justify="center"
      css={{ minHeight: '100vh' }}
    >
      <Card css={{ mw: '420px', p: '20px' }} variant="bordered">
        <Card.Body>
          <div>
            <Text size={24} weight="bold" css={{ as: 'center', mb: '20px', }}>
              {isSigningUp ? 'Join Us' : 'Sign In'}
            </Text>
            {isSigningUp ? <SignupForm /> : <SigninForm />}
            <Button light color="secondary" onClick={toggleForm} size="xs">
              {isSigningUp ? 'Already have an account? Sign In' : 'Don\'t have an account? Sign Up'}
            </Button>
          </div>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default AuthenticationPage;
