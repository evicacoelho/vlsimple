import React, { useRef, useState } from "react";
import axios from "axios";

const MediaUploadModal = ({
    isOpen,
    onClose,
    onUploadSuccess,
}: {
    isOpen: boolean;
    onClose: () => void;
    onUploadSuccess: () => void;
}) => {
        const fileInputRef = useRef<HTMLInputElement | null>(null);
        const [error, setError] = useState<string | null>(null);

        if(!isOpen) return null;

        const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
            if (e.target.files) {
                const formData = new FormData();
                Array.from(e.target.files).forEach((file) => {
                    formData.append("files", file);
                });

                try {
                    await axios.post(
                        "http://localhost:8080/api/media/file", formData, {
                            headers: {"Content-Type": "multipart/form-data"}
                        });
                    onUploadSuccess();
                } catch (err) {
                    setError("Failed to upload files");
                }
            }
        };

        const handleFolderUpload = async (folderPath: string) => {
            try {
                await axios.post("http://localhost:8080/api/media/folder", { folderPath });
                onUploadSuccess();
            } catch (err) {
                setError("Failed to upload folder");
            }
        };

        return (
            <div className="modal">
                <div className="modal-content">
                    <h2>Upload Media</h2>
                    <input
                        type="file"
                        multiple
                        ref={fileInputRef}
                        onChange={handleFileChange}
                    />
                    <button onClick={() => handleFolderUpload('some folder in the future')}>Upload Folder</button>
                    {error && <p>error</p>}
                    <button onClick={onClose}>Close</button>
                </div>
            </div>
        )
    };

export default MediaUploadModal;
