import {
    Button, Input,
    Spacer
} from '@nextui-org/react';
import router from 'next/router';
import { useState } from 'react';

type SigninFormValues = {
    username: string;
    password: string;
};

const SigninForm: React.FC = () => {
    const [formValues, setFormValues] = useState<SigninFormValues>({
        username: '',
        password: '',
    });

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormValues((prevFormValues) => ({
            ...prevFormValues,
            [name]: value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const response = await fetch('/api/auth/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formValues),
            });

            if (response.ok) {
                const { token, validity } = await response.json();
                console.log('User logged in successfully');
                localStorage.setItem('token', token);
                localStorage.setItem('validity', validity);
                localStorage.setItem('date', new Date().getTime().toString());
                localStorage.setItem('username', formValues.username);
                router.push('/');
            } else {
                console.log('Invalid credentials. Please try again.');
            }
        } catch (error) {
            console.error('An error occurred during login:', error);
        }

        setFormValues({ username: '', password: '' });
    };



    return (

        <div>
            <form onSubmit={handleSubmit}>
                <Input
                    name="username"
                    value={formValues.username}
                    onChange={handleChange}
                    placeholder="Username"
                    required
                    width="100%"
                    size="lg"
                    css={{ mb: '10px' }}
                />
                <Spacer y={1} />
                <Input.Password
                    name="password"
                    value={formValues.password}
                    onChange={handleChange}
                    placeholder="Password"
                    required
                    width="100%"
                    size="lg"
                    css={{ mb: '10px' }}
                />
                <Spacer y={1} />
                <Button
                    type="submit"
                    color="secondary"
                    size="lg"
                    css={{ mb: '10px' }}
                >
                    Sign In
                </Button>
            </form>

        </div>
    );
};

export default SigninForm;
