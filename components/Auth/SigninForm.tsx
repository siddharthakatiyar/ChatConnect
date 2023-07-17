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

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
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
                <div>
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={formValues.username}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formValues.password}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default SigninForm;
