import { useState, useEffect } from 'react';

export default function CreateShiftModal({ isOpen, onClose, clients = [], services = [], onSave, initialData }) {
    const [formData, setFormData] = useState({
        clientId: '',
        serviceId: '',
        totalHours: '',
        zipcode: ''
    });

    useEffect(() => {
        if (initialData) {
            setFormData({
                clientId: initialData.client?.clientId || '',
                serviceId: initialData.service?.serviceId || initialData.service?.servicesId || '',
                totalHours: initialData.totalHours || '',
                zipcode: initialData.zipcode || ''
            });
        } else {
            setFormData({ clientId: '', serviceId: '', totalHours: '', zipcode: '' });
        }
    }, [initialData, isOpen]);

    if (!isOpen) return null;

    const handleSubmit = (e) => {
        e.preventDefault();

        const payload = {
            client: {
                clientId: parseInt(formData.clientId, 10)
            },
            service: {
                servicesId: parseInt(formData.serviceId, 10)
            },
            totalHours: parseInt(formData.totalHours, 10),
            zipcode: formData.zipcode,
            available: true
        };

        console.log("DEBUG Payload sending to Backend:", payload);
        onSave(payload);
    };

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 backdrop-blur-sm">
            <div className="w-full max-w-md rounded-xl bg-white p-6 shadow-2xl">
                <h2 className="text-xl font-bold">
                    {initialData ? 'Edit Shift' : 'Post New Shift'}
                </h2>

                <form onSubmit={handleSubmit} className="space-y-4">
                    {/* Client Selection */}
                    <div>
                        <label className="block text-sm font-semibold text-gray-700 mb-1">Client</label>
                        <select
                            required
                            className="w-full rounded-lg border border-gray-300 p-2.5 focus:ring-2 focus:ring-indigo-500"
                            value={formData.clientId}
                            onChange={e => setFormData({...formData, clientId: e.target.value})}
                        >
                            <option value="">-- Select Client --</option>
                            {clients.map(c => (
                                <option key={c.clientId} value={c.clientId}>
                                    {c.firstName} {c.lastName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Service Selection */}
                    <div>
                        <label className="block text-sm font-semibold text-gray-700 mb-1">Service Type</label>
                        <select
                            required
                            className="w-full rounded-lg border border-gray-300 p-2.5 focus:ring-2 focus:ring-indigo-500"
                            value={formData.serviceId}
                            onChange={e => {
                                console.log("Selected Value:", e.target.value);
                                setFormData({...formData, serviceId: e.target.value});
                            }}
                        >
                            <option value="">-- Select Service --</option>
                            {services.map(s => {
                                const actualId = s.servicesId || s.serviceId || s.id;
                                return (
                                    <option key={actualId} value={actualId}>
                                        {s.serviceName}
                                    </option>
                                );
                            })}
                        </select>
                    </div>

                    {/* Duration and Zip */}
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-semibold text-gray-700 mb-1">Total Hours</label>
                            <input
                                type="number"
                                required
                                max={"24"}
                                className="w-full rounded-lg border border-gray-300 p-2.5"
                                value={formData.totalHours}
                                onChange={e => setFormData({...formData, totalHours: e.target.value})}
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-semibold text-gray-700 mb-1">Zipcode</label>
                            <input
                                type="text"
                                required
                                max={"99999"}
                                className="w-full rounded-lg border border-gray-300 p-2.5"
                                value={formData.zipcode}
                                onChange={e => setFormData({...formData, zipcode: e.target.value})}
                            />
                        </div>
                    </div>

                    <div className="mt-6 flex justify-end gap-3">
                        <button
                            type="button"
                            onClick={onClose}
                            className="px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-100 rounded-lg"
                        >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            className="rounded-lg bg-indigo-600 px-4 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500"
                        >
                            Create Shift
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}