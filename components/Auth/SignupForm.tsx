import {
    Button,
    Input,
    Spacer
} from '@nextui-org/react';
import { useState } from "react";

type SignupFormValues = {
    firstName: string;
    lastName: string;
    username: string;
    password: string;
    confirmPassword: string;
};

const SignUpForm: React.FC = () => {
    const [formValues, setFormValues] = useState<SignupFormValues>({
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        confirmPassword: '',
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
        if (formValues.password !== formValues.confirmPassword) {
            console.log('Passwords do not match');
            return;
        }
        try {
            const response = await fetch('/api/auth/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formValues),
            });

            if (response.ok) {
                console.log('User created successfully');
            } else {
                console.log('Something went wrong. Please try again.');
            }
        } catch (error) {
            console.error('An error occurred during signup:', error);
        }

        setFormValues({
            firstName: '',
            lastName: '',
            username: '',
            password: '',
            confirmPassword: '',
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <Input
                    name="FirstName"
                    value={formValues.firstName}
                    onChange={handleChange}
                    placeholder="First Name"
                    required
                    width="100%"
                    size="lg"
                    css={{ mb: '10px' }}
                />
                <Spacer y={1} />
                <Input
                    name="LastName"
                    value={formValues.lastName}
                    onChange={handleChange}
                    placeholder="Last Name"
                    required
                    width="100%"
                    size="lg"
                    css={{ mb: '10px' }}
                />
                <Spacer y={1} />
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
                <Input.Password
                    name="confirmPassword"
                    value={formValues.confirmPassword}
                    onChange={handleChange}
                    placeholder="Confirm Password"
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
                    Sign Up
                </Button>
            </form>

        </div>
    );
}

export default SignUpForm;