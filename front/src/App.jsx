// src/App.jsx
import { BrowserRouter, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import { useState, useEffect } from 'react';
import LoginPage from './pages/LoginPage';
import ClientsPage from './pages/ClientsPage';
import ShiftsPage from "./pages/ShiftsPage.jsx";
import ReportsPage from "./pages/ReportsPage.jsx";
import ProtectedRoute from './auth/ProtectedRoute';
import { AuthProvider, useAuth } from './auth/AuthContext';
import Navbar from './components/Navbar';

// API imports
import { getAllClients } from './api/clientApi';
import { getAllShifts } from './api/shiftApi';
import { getAllServices } from './api/serviceApi';

// 1. Define Layout First
function AppLayout() {
    return (
        <div className="min-h-screen bg-gray-50">
            <Navbar />
            <main>
                <Outlet />
            </main>
        </div>
    );
}

// 2. Define Content Logic
function AppContent() {
    const [clients, setClients] = useState([]);
    const [shifts, setShifts] = useState([]);
    const [services, setServices] = useState([]);
    const { token } = useAuth();

    const loadData = () => {
        if (token) {
            Promise.all([getAllClients(), getAllShifts(), getAllServices()])
                .then(([clientRes, shiftRes, serviceRes]) => {
                    setClients(clientRes.data || []);
                    setShifts(shiftRes.data || []);
                    setServices(serviceRes.data || []);
                })
                .catch(err => console.error("Error loading app data", err));
        }
    };

    useEffect(() => {
        loadData();
    }, [token]);

    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace />} />
            <Route path="/login" element={<LoginPage />} />

            <Route element={<ProtectedRoute><AppLayout /></ProtectedRoute>}>
                <Route path="/clients" element={<ClientsPage clients={clients} />} />
                <Route path="/shifts" element={
                    <ShiftsPage
                        shifts={shifts}
                        clients={clients}
                        services={services}
                        refreshData={loadData}
                    />
                } />
                <Route path="/reports" element={<ReportsPage clients={clients} shifts={shifts} />} />
            </Route>

            <Route path="*" element={<Navigate to="/clients" replace />} />
        </Routes>
    );
}

// 3. Define Main Entry Point
function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <AppContent />
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;